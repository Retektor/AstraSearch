import { useEffect, useState } from "react";
import type { User } from "./api/userApi";
import { userApi } from "./api/userApi";

function App() {
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        const username = window.location.pathname.split("/").pop();
        if (username) {
            userApi
                .getUserByUsername(username)
                .then(setUser)
                .catch(() => {
                    setUser(null);
                });
        }
    }, []);

    if (user === null) {
        return <h1>Пользователь не найден!</h1>;
    }

    return (
        <>
            <h1>Никнейм: {user.username}</h1>
            <p>ID: {user.id}</p>
            <p>Пароль: {user.password}</p>
            <p>Имя: {user?.firstName}</p>
            <p>Фамилия: {user?.lastName}</p>
            <p>Почта: {user.email}</p>
        </>
    );
}

export default App;
