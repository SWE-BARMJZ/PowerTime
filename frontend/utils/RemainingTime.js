const floor = (num) => Math.floor(num);

const getRemainingTime = (deadline) => {
    const d = new Date(deadline);

    const milliseconds = (d.getTime() - Date.now());
    const seconds = milliseconds / 1000;
    const minutes = seconds / 60;
    const hours = minutes / 60;
  
    return {
      days: floor(hours / 24),
      hours: floor(hours % 24),
      minutes: floor(minutes % 60), 
      seconds: floor(seconds % 60)
    };
};

export default getRemainingTime;