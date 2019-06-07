import React from 'react'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa'
import batchTypes from '../../shared/batchTypes'
/*sku
upc
name
brand
minStock
providerPrice
retailPrice
description*/

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

const columns = [
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                SKU
              </div>
        ),
        accessor: 'sku',
        Cell: props => props.value != null ? props.value : ' - '
    },
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
                Marca
              </div>
        ),
        accessor: 'brand',
        Cell: props => props.value
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Costo
              </div>
        ),
        accessor: 'providerPrice',
        Cell: props => props.value
    },

    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Venta
              </div>
        ),
        accessor: 'retailPrice',
        Cell: props => props.value
    }, {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Tipo Lote
              </div>
        ),
        accessor: 'type',
        Cell: props => {
            return batchTypes[props.value].text
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