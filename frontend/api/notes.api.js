import { BACKEND_URL } from "./const";

export const getFolders = async (token) => {
    const url = `${BACKEND_URL}/folder/get`;
  
    const response = await fetch(url, {
      method: "GET",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response;
  };

  export const getNotes = async (folderId, token) => {
    const url = `${BACKEND_URL}/note/getFolderNotes?folderId=${folderId}`;
  
    const response = await fetch(url, {
      method: "GET",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response;
  };

  export const getStarredNotes = async (token) => {
    const url = `${BACKEND_URL}/note/getStarredNotes`;
  
    const response = await fetch(url, {
      method: "GET",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response;
  };

  export const createNote = async (folderId, noteTitle, token) => {
    const url = `${BACKEND_URL}/note/create?folderId=${folderId}&noteTitle=${noteTitle}`;

    const response = await fetch(url, {
        method: "POST",
        headers: { "Content-type": "application/json" , Authorization: `Bearer ${token}`},
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      console.log(response.headers)
      if (response.status !== 200) {
        const message = "Folder has the same name as this";
        throw new Error(message);
      }
      
      return response;
  }

  export const createFolder = async (folderName, token) => {
    const url = `${BACKEND_URL}/folder/create`;

    const response = await fetch(url, {
        method: "POST",
        headers: { "Content-type": "application/json" , Authorization: `Bearer ${token}`},
        body: folderName,
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      console.log(response.headers)
      if (response.status !== 200) {
        const message = "Folder has the same name as this";
        throw new Error(message);
      }
      
      return response;
  }

  export const deleteFolder = async (folderId, token) => {
    const url = `${BACKEND_URL}/folder/delete?folderId=${folderId}`;

    const response = await fetch(url, {
        method: "DELETE",
        headers: { "Content-type": "application/json" , Authorization: `Bearer ${token}`},
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      if (response.status !== 200) {
        const message = "I don't know yet";
        throw new Error(message);
      }
      
      return response;
  }

  export const renameFolder = async (folderId, newName, token) => {
    const url = `${BACKEND_URL}/folder/modify?folderId=${folderId}&folderName=${newName}`;

    const response = await fetch(url, {
        method: "PUT",
        headers: { "Content-type": "application/json", Authorization: `Bearer ${token}` },
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      console.log(response.headers)
      if (response.status !== 200) {
        const message = "Folder has the same name as this";
        throw new Error(message);
      }
      
      return response;
  }

  export const starNote = async (noteId, token) => {
    const url = `${BACKEND_URL}/note/alterStar?noteId=${noteId}`;

    const response = await fetch(url, {
        method: "PUT",
        headers: { "Content-type": "application/json", Authorization: `Bearer ${token}` },
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      console.log(response.headers)
      if (response.status !== 200) {
        const message = "Folder has the same name as this";
        throw new Error(message);
      }
      
      return response;
  }

  export const editNote = async (noteId, title, content, token) => {
    const url = `${BACKEND_URL}/note/modify?noteId=${noteId}`;

    const stringBody = {
      title: `${title}`,
      content: `${content}`
    }

    const response = await fetch(url, {
        method: "PUT",
        headers: { "Content-type": "application/json", Authorization: `Bearer ${token}` },
        body: JSON.stringify(stringBody),
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      if (response.status !== 200) {
        const message = "I don't Know Yet";
        throw new Error(message);
      }
      
      return response;
  }

  export const MoveNoteToFolder = async (newFolderId, noteId, token) => {
    const url = `${BACKEND_URL}/note/moveNote?newFolderId=${newFolderId}&noteId=${noteId}`;

    const response = await fetch(url, {
        method: "PUT",
        headers: { "Content-type": "application/json", Authorization: `Bearer ${token}` },
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      console.log(response.headers)
      if (response.status !== 200) {
        const message = "I don't know yet.";
        throw new Error(message);
      }
      
      return response;
  }

  export const deleteNote = async (noteId, token) => {
    const url = `${BACKEND_URL}/note/deleteNote?noteId=${noteId}`;

    const response = await fetch(url, {
        method: "DELETE",
        headers: { "Content-type": "application/json" , Authorization: `Bearer ${token}`},
      }).catch((error) => {
        throw new Error("Problem connecting with the server!");
      });

      if (response.status !== 200) {
        const message = "I don't know yet";
        throw new Error(message);
      }
      
      return response;
  }