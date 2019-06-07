import { combineReducers } from 'redux'
import { getRoles } from '../../common/auth'

import list from './list'
import remove from './remove'
import update from './update'
import create from './create'

let roles = getRoles();
let reducer = combineReducers({})

if (roles.indexOf("ADMIN") >= 0) {
    reducer = combineReducers({
        list, remove, update, create
    });
}

export default reducer;