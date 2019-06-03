import React from 'react'

const Presentation = (props) => {
    return <button onClick={()=>props.action()}> a button </button>
}

export default Presentation