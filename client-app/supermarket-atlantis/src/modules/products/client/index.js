import { combineReducers } from 'redux'

import search from './search'
import view from './view'
import { isClient } from '../../../common/auth';

let reducer;
if (isClient()) {
    reducer = combineReducers({
        search, view
    });
} else {
    reducer = combineReducers({});
}

export default reducer