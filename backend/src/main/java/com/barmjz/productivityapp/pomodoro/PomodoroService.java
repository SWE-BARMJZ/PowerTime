package com.barmjz.productivityapp.pomodoro;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PomodoroService {
    private final PomodoroRepo pomoRepo;

    public Pomodoro get(Long userId) {
        if (!pomoRepo.existsPomodoroByUserId(userId)) {
            Pomodoro p = Pomodoro
                    .builder()
                    .studyTime(25)
                    .breakTime(5)
                    .build();
            pomoRepo.save(p);
            return p;
        }
        Pomodoro p = pomoRepo.getPomodoroByUserId(userId);
        if (p instanceof PomodoroSession && !((PomodoroSession) p).isPaused()){
            long curRemainingTime = System.currentTimeMillis()/1000 - ((PomodoroSession) p).getStartTime();
            ((PomodoroSession) p).setRemainingTimeInSecs(curRemainingTime);
        }
        pomoRepo.save(p);
        return p;
    }

    public String startStudy(Long pomoId) {
        Pomodoro p = pomoRepo.getReferenceById(pomoId);
        p = PomodoroSession
                .builder()
                .id(p.getId())
                .isStudying(true)
                .startTime(System.currentTimeMillis()/1000)
                .remainingTimeInSecs(p.studyTime* 60L)
                .build();
        pomoRepo.save(p);
        return "Pomodoro Session Started";
    }

    public String startBreak(Long pomoId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getReferenceById(pomoId);
        p.setStudying(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        p.setRemainingTimeInSecs(p.breakTime*60L);
        pomoRepo.save(p);
        return "Pomodoro Break Started";
    }

    public String pause(Long pomoId, Long remainingTime) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getReferenceById(pomoId);
        p.setPaused(true);
        //REMAINING TIME IS RECEIVED IN SECONDS
        p.setRemainingTimeInSecs(remainingTime);
        pomoRepo.save(p);
        return "Pomodoro Paused";
    }

    public String resume(Long pomoId) {
        PomodoroSession p = (PomodoroSession) pomoRepo.getReferenceById(pomoId);
        p.setPaused(false);
        p.setStartTime(System.currentTimeMillis()/1000);
        pomoRepo.save(p);
        return "Pomodoro Resumed";
    }

    public String set(Pomodoro newPomo) {
        Pomodoro p = pomoRepo.getReferenceById(newPomo.getId());
        p.setStudyTime(newPomo.studyTime);
        p.setBreakTime(newPomo.breakTime);
        pomoRepo.save(p);
        return "Pomodoro Set";
    }

}
