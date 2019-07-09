import { combineReducers } from 'redux'

import search from './search'
import item from './item'
import { isClient } from '../../../common/auth';

let reducer;
if (isClient()) {
    reducer = combineReducers({
        search, item
    });
} else {
    reducer = combineReducers({});
}

export default reducer