import { combineReducers } from 'redux'
import { getRoles } from '../../common/auth'
import admin from './admin'
import client from './client'

let roles = getRoles();
let reducer = null

if (roles.indexOf("ADMIN") >= 0) {
    reducer = admin
}

export default reducer;