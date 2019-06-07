import * as jwt from 'jsonwebtoken'

export const getRoles = () => {
    let token = localStorage.getItem("JWT_LOGIN")
    if (token) {
        token = token.split("Bearer ")[1]
        var decoded = jwt.decode(token, { complete: true });
        return decoded.payload.rol;
    } else {
        return ""
    }
}

export const isAdmin = () => {
    let roles = getRoles();
    return roles.indexOf("ADMIN") >= 0
}

export const isClient = () => {
    let roles = getRoles();
    return roles.indexOf("CLIENT") >= 0
}

export const isUser = () => {
    let roles = getRoles();
    return roles.indexOf("USER") >= 0
}
