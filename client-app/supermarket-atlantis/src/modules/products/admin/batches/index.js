import { combineReducers } from 'redux'
import { authHelper } from '../../../shared/index'

import list from './list'
import view from './view'
import create from './create'
import update from './update'
import supply from './supply'
// import remove from './remove'

let reducer = combineReducers({})

if (authHelper.isAdmin() >= 0) {
    reducer = combineReducers({
        list, update, create, view, supply
    });
}

export default reducer;