import React from 'react'
import _ from 'lodash'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'
import batchTypes from '../../../shared/batchTypes'

const renderToolbar = ({ ...props }) => {
    let viewButton = (
        <Link to={`/product/view/${props.value}`}>
            <button >
                <FaSearch />
            </button>
        </Link>
    )

    let editButton = (
        <Link to={`/product/edit/${props.value}`}>
            <button >
                <FaEdit />
            </button>
        </Link>
    )

    let removeButton = (
        <Link to={`/product/remove/${props.value}`}>
            <button >
                <FaTrash />
            </button>
        </Link>
    )

    return (<span>
        {viewButton} {' '}
        {editButton} {' '}
        {removeButton} {' '}
    </span>)
}

export default renderToolbar;