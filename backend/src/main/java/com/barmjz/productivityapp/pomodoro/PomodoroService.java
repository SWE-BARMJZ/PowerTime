package com.barmjz.productivityapp.pomodoro;

import com.barmjz.productivityapp.user.User;
import com.barmjz.productivityapp.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PomodoroService {
    private final PomodoroRepo pomoRepo;

    public Pomodoro get(User currentUser) {
        //First time?
        if (!pomoRepo.existsPomodoroByUserId(currentUser.getId())) {
            return createPomo(25,5,currentUser);
        }
        Pomodoro p = pomoRepo.getPomodoroByUserId(currentUser.getId());
        if (p instanceof PomodoroSession pSession && !((PomodoroSession) p).isPaused()){
            long curRemainingTime = System.currentTimeMillis()/1000 - pSession.getStartTime();
            long lastRemainingTime = pSession.getRemainingTimeInSecs();
            //Session still running
            if (curRemainingTime<=lastRemainingTime) {
                pSession.setRemainingTimeInSecs(curRemainingTime);
                pomoRepo.save(p);
            //session ended
            }else{
                if (pSession.isStudying())
                    return endStudyUpdate(pSession);
                else
                    return resetUpdate(pSession,currentUser);
            }
        }
        //Paused Session
        //No session active
        return p;
    }

    private Pomodoro createPomo(int studytime, int breakTime, User user) {
        Pomodoro p = Pomodoro
                .builder()
                .studyTime(studytime)
                .breakTime(breakTime)
                .user(user)
                .build();
        pomoRepo.save(p);
        return p;
    }

    public String startStudy(User currentUser) {
        Pomodoro deletedPomodoro = pomoRepo.getPomodoroByUserId(currentUser.getId());
        pomoRepo.deleteById(deletedPomodoro.getId());
        PomodoroSession p = initSession(deletedPomodoro,currentUser);
        pomoRepo.save(p);
        return "Pomodoro Session Started";
    }

    private PomodoroSession initSession(Pomodoro p,User user) {
        return PomodoroSession
                .builder()
                .id(p.getId())
                .studyTime(p.getStudyTime())//25
                .breakTime(p.getBreakTime())//5
                .user(user)
                .isStudying(true)
                .startTime(System.currentTimeMillis()/1000)//6:00
                .remainingTimeInSecs(p.studyTime* 60L)//25min
                .build();
    }

    public String startBreak(Long userId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setStudying(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        p.setRemainingTimeInSecs(p.breakTime*60L);
        pomoRepo.save(p);
        return "Pomodoro Break Started";
    }

    public String pause(Long remainingTime, Long userId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setPaused(true);
        //REMAINING TIME IS RECEIVED IN SECONDS
        p.setRemainingTimeInSecs(remainingTime);
        pomoRepo.save(p);
        return "Pomodoro Paused";
    }

    public String resume(Long userId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setPaused(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        pomoRepo.save(p);
        return "Pomodoro Resumed";
    }

    public String endStudy(Long userId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        endStudyUpdate(p);
        return "Break Time !";
    }

    private PomodoroSession endStudyUpdate(PomodoroSession p) {
        p.setPaused(true);
        p.setStudying(false);
        p.setRemainingTimeInSecs(p.breakTime* 60L);
        pomoRepo.save(p);
        return p;
    }

    public String set(int newStudyTime, int newBreakTime,Long userId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setStudyTime(newStudyTime);
        p.setBreakTime(newBreakTime);
        pomoRepo.save(p);
        return "Pomodoro Set";
    }

    public String reset(User currentUser){
        Pomodoro deletedSession =  pomoRepo.getPomodoroByUserId(currentUser.getId());
        resetUpdate(deletedSession,currentUser);
        return "Pomodoro Reset";
    }

    private Pomodoro resetUpdate(Pomodoro deletedSession,User currentUser) {
        pomoRepo.deleteById(deletedSession.getId());
        return createPomo(deletedSession.getStudyTime(),deletedSession.getBreakTime(),currentUser);
    }
}
