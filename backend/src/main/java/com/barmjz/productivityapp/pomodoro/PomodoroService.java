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
    private final UserRepo userRepo;

    private Long getCurrentUserId(){
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.getUserByEmail(userAuthentication.getName()).get().getId();
    }

    private User getCurrentUser(){
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.getUserByEmail(userAuthentication.getName()).get();
    }

    public Pomodoro get() {
        Long userId = getCurrentUserId();
        if (!pomoRepo.existsPomodoroByUserId(userId)) {
            return createPomo(25,5);
        }
        Pomodoro p = pomoRepo.getPomodoroByUserId(userId);
        if (p instanceof PomodoroSession pSession && !((PomodoroSession) p).isPaused()){
            long curRemainingTime = System.currentTimeMillis()/1000 - pSession.getStartTime();
            long lastRemainingTime = pSession.getRemainingTimeInSecs();
            if (curRemainingTime<=lastRemainingTime)
                pSession.setRemainingTimeInSecs(curRemainingTime);
            else {
                if (pSession.isStudying())
                    return endStudyUpdate(pSession);
                else
                    return resetUpdate(pSession);
            }
        }
        pomoRepo.save(p);
        return p;
    }

    private Pomodoro createPomo(int studytime, int breakTime) {
        Pomodoro p = Pomodoro
                .builder()
                .studyTime(studytime)
                .breakTime(breakTime)
                .user(getCurrentUser())
                .build();
        pomoRepo.save(p);
        return p;
    }

    public String startStudy() {
        Long userId = getCurrentUserId();
        Pomodoro p = pomoRepo.getPomodoroByUserId(userId);
        p = initSession(p);
        pomoRepo.save(p);
        return "Pomodoro Session Started";
    }

    private Pomodoro initSession(Pomodoro p) {
        return PomodoroSession
                .builder()
                .id(p.getId())
                .studyTime(p.getStudyTime())//25
                .breakTime(p.getBreakTime())//5
                .user(getCurrentUser())
                .isStudying(true)
                .startTime(System.currentTimeMillis()/1000)//6:00
                .remainingTimeInSecs(p.studyTime* 60L)//25min
                .build();
    }

    public String startBreak() {
        Long userId = getCurrentUserId();
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setStudying(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        p.setRemainingTimeInSecs(p.breakTime*60L);
        pomoRepo.save(p);
        return "Pomodoro Break Started";
    }

    public String pause(Long remainingTime) {
        Long userId = getCurrentUserId();
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setPaused(true);
        //REMAINING TIME IS RECEIVED IN SECONDS
        p.setRemainingTimeInSecs(remainingTime);
        pomoRepo.save(p);
        return "Pomodoro Paused";
    }

    public String resume() {
        Long userId = getCurrentUserId();
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setPaused(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        pomoRepo.save(p);
        return "Pomodoro Resumed";
    }

    public String endStudy() {
        Long userId = getCurrentUserId();
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

    public String set(int newStudyTime, int newBreakTime) {
        Long userId = getCurrentUserId();
        PomodoroSession p = (PomodoroSession) pomoRepo.getPomodoroByUserId(userId);
        p.setStudyTime(newStudyTime);
        p.setBreakTime(newBreakTime);
        pomoRepo.save(p);
        return "Pomodoro Set";
    }

    public String reset(){
        Long userId = getCurrentUserId();
        Pomodoro deletedSession =  pomoRepo.getPomodoroByUserId(userId);
        resetUpdate(deletedSession);
        return "Pomodoro Reset";
    }

    private Pomodoro resetUpdate(Pomodoro deletedSession) {
        pomoRepo.deleteById(deletedSession.getId());
        return createPomo(deletedSession.getStudyTime(),deletedSession.getBreakTime());
    }
}
