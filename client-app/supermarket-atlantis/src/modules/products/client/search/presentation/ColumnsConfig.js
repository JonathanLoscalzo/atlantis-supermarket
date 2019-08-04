import React from 'react'
import { Link } from 'react-router-dom'
import { FaEdit, FaTrash, FaSearch, FaPlusCircle } from 'react-icons/fa'
import batchTypes from '../../../shared/batchTypes'
import { Button } from 'reactstrap';
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
        <Link to={`/shopping/view/${props.value}`}>
            <button >
                <FaSearch />
            </button>
        </Link>
    )
    // props.original
    let addItem = props.original.isPurchable ?
        (
            <Link to=
                {{
                    pathname: "/shopping/additems",
                    state: {
                        product: props.original,
                        id: props.value
                    }
                }}>
                <button>
                    <FaPlusCircle />
                </button>
            </Link >
        ) : (<div></div>);

    return (<span>
        {viewButton} {' '}
        {addItem} {' '}
    </span>)
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
        sortable: false,
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
        sortable: false,
        Cell: props => props.value
    },

    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Precio
              </div>
        ),
        accessor: 'retailPrice',
        Cell: props => `$${props.value}`
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Stock
              </div>
        ),
        accessor: 'units',
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
        sortable: false,
        Cell: renderToolbar
    }
]

export default columns;