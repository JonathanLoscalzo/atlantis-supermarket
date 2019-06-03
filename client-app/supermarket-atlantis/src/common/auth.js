import * as jwt from 'jsonwebtoken'

export const getRoles = () => {
    let token = localStorage.getItem("JWT_LOGIN").split("Bearer ")[1]
    var decoded = jwt.decode(token, {complete: true});
    return decoded.payload.rol;
}
