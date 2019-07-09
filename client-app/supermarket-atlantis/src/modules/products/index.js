import {  isAdmin, isClient } from '../../common/auth'

import admin from './admin'
import client from './client'

let reducer = null

if (isAdmin()) {
    reducer = admin
} else if (isClient()) {
    reducer = client;
}

export default reducer;