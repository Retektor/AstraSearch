import axios from "axios";

const apiClient = axios.create({
    baseURL: "/api",
    timeout: 5000,
    headers: {
        "Content-Type": "application/json",
    },
});

export interface User {
    id: number;
    username: string;
    password: string;
    firstName?: string;
    lastName?: string;
    email: string;
}

export const userApi = {
    getUserByUsername: (username: string): Promise<User> => {
        return apiClient
            .get<User>(`/users/${username}`)
            .then((response) => response.data);
    },
};
