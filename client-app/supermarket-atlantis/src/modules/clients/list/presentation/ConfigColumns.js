import React from 'react'
import moment from 'moment'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'

const renderToolbar = ({ ...props }) => {
    let editButton;
    editButton = (
        <Link to={`/clients/view/${props.value}`}>
            <button >
                <FaEdit /> ver
            </button>
        </Link>
    )

    return <span>
        {/* <Link to={`/repair/view/${props.value}`}>
            <button>
                <FaSearch />
            </button>
        </Link> {' '} */}
        {editButton} {' '}
        {/* <Link to={`/repair/remove/${props.value}`}>
            <button>
                <FaTrash />
            </button>
        </Link> */}
    </span>
}

const columns = [
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Nombre
              </div>
        ),
        accessor: 'name',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Apellido
              </div>
        ),
        accessor: 'surname',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Document 
              </div>
        ),
        accessor: 'document',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Usuario
              </div>
        ),
        accessor: 'username',
        Cell: props => props.value
    },

    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Email
              </div>
        ),
        accessor: 'email',
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