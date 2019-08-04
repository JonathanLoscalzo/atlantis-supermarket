export default function handler(handlers, state, action = {}) {
    const handler = handlers[action.type];
    return handler ? handler(state, action) : state;
}