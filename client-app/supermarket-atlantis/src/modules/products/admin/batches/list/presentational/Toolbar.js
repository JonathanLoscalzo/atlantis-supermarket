import React from 'react'
import _ from 'lodash'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'
import { MdPlusOne } from 'react-icons/md'
import batchTypes from '../../../shared/batchTypes'

const renderToolbar = ({ ...props }) => {
    let addBatch = (
        <Link to={`/batch/new/${props.value}`}>
            <button >
                <MdPlusOne />
            </button>
        </Link>
    )

    // let editButton = (
    //     <Link to={`/product/edit/${props.value}`}>
    //         <button >
    //             <FaEdit />
    //         </button>
    //     </Link>
    // )

    // let removeButton = (
    //     <Link to={`/product/remove/${props.value}`}>
    //         <button >
    //             <FaTrash />
    //         </button>
    //     </Link>
    // )

    return (<span>
        {addBatch} {' '}
        {/* {editButton} {' '} */}
        {/* {removeButton} {' '} */}
    </span>)
}

export default renderToolbar;