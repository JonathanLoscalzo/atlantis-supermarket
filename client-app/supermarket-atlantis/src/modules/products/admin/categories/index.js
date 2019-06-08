import { combineReducers } from 'redux'
import { authHelper } from '../../../shared/index'

import list from './list'
import remove from './remove'
import update from './update'
import create from './create'

let reducer = combineReducers({})

if (authHelper.isAdmin() >= 0) {
    reducer = combineReducers({
        list, remove, update, create
    });
}

export default reducer;