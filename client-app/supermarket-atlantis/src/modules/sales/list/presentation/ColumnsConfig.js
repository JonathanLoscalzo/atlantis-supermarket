import React from 'react'
import moment from 'moment'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'

const renderToolbar = ({ ...props }) => {
    let viewButton = (
        <Link to={`/sale/view/${props.value}`}>
            <button >
                <FaSearch />
            </button>
        </Link>
    )


    return <span>
        {viewButton} {' '}
    </span>
}

const columns = [
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Estados
              </div>
        ),
        accessor: 'state',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Items
              </div>
        ),
        accessor: 'items',
        Cell: props => {
            return `${props.value.length} items`
        }
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