import React from 'react'
import moment from 'moment'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'

const renderToolbar = ({ ...props }) => {
    let viewButton = (
        <Link to={`/category/view/${props.value}`}>
            <button >
                <FaSearch />
            </button>
        </Link>
    )

    let editButton = (
        <Link to={`/category/edit/${props.value}`}>
            <button >
                <FaEdit />
            </button>
        </Link>
    )

    let removeButton = (
        <Link to={`/category/remove/${props.value}`}>
            <button >
                <FaTrash />
            </button>
        </Link>
    )

    return <span>
        {viewButton} {' '}
        {editButton} {' '}
        {removeButton} {' '}
    </span>
}

const columns = [
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Descripcion
              </div>
        ),
        accessor: 'description',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Opciones
              </div>
        ),
        accessor: 'id',
        Cell: renderToolbar
    }
]

export default columns;