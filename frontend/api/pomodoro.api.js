import { BACKEND_URL } from "./const";

export const getPomodoro = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/`;
    const response = await fetch(url,{
        method:"GET" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.json();
}

export const startStudy = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/`;
    const response = await fetch(url,{
        method:"POST" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const startBreak = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/break`;
    const response = await fetch(url,{
        method:"POST" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const pause = async (token,remainingTime) => {
    const url = `${BACKEND_URL}/api/pomo/${remainingTime}/pause`;
    const response = await fetch(url,{
        method:"POST" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const resume = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/resume`;
    const response = await fetch(url,{
        method:"POST" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const endStudy = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/endStudy`;
    const response = await fetch(url,{
        method:"POST" , headers: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
    });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const setPomodoro = async (token, studyTime, breakTime) => {
    const url = `${BACKEND_URL}/api/pomo/${studyTime}/${breakTime}`;
    const response = await fetch(url,{
        method:"PUT" , headers: {Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}

export const resetPomodoro = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/reset`;
    const response = await fetch(url,{
        method:"POST" , headers: {Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
    });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response.text();
}