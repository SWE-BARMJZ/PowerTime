import { BACKEND_URL } from "./const";

export const getPomodoro = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/`;
    const response = await fetch(url,{
        method:"GET" , vheaders: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}

export const startStudy = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/`;
    const response = await fetch(url,{
        method:"POST" , vheaders: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}

export const startBreak = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/break`;
    const response = await fetch(url,{
        method:"POST" , vheaders: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}

export const pause = async (token,remainingTime) => {
    const url = `${BACKEND_URL}/api/pomo/${remainingTime}/pause`;
    const response = await fetch(url,{
        method:"POST" , vheaders: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}

export const resume = async (token) => {
    const url = `${BACKEND_URL}/api/pomo/resume`;
    const response = await fetch(url,{
        method:"POST" , vheaders: { Authorization: `Bearer ${token}`}
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}

export const setPomodoro = async (token, studyTime, breakTime) => {
    const url = `${BACKEND_URL}/api/pomo/`;
    const stringBody = {
        studyTime: `${studyTime}`,
        breakTime: `${breakTime}`
      }
    const response = await fetch(url,{
        method:"PUT" , vheaders: {"Content-type": "application/json",
        Authorization: `Bearer ${token}`},
        body:JSON.stringify(stringBody)
    }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });
    if (response.status !== 200) {
        throw new Error(response.status);
    }
    return response;
}