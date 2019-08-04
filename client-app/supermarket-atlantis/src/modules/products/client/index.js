import { combineReducers } from 'redux'

import search from './search'
import view from './view'
import addItems from './addItems'
import { isClient } from '../../../common/auth';

let reducer;
if (isClient()) {
    reducer = combineReducers({
        search, view, addItems
    });
} else {
    reducer = combineReducers({});
}

export default reducer