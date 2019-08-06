import { combineReducers } from 'redux'
import { isClient } from '../../common/auth'

import list from './list'
import view from './view'

let reducer = (state = {}, action) => state

if (isClient()) {
    reducer = combineReducers({
        list, view
    });
}

export default reducer;