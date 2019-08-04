import React from 'react'
import { Modal, ModalHeader, ModalBody  } from 'reactstrap'
import Form from '../form/Form'

export default props => {
    return (
        <div>
            <Modal isOpen={props.isOpen}>
                <ModalHeader>Agregar Items al carrito</ModalHeader>
                <ModalBody>
                    <Form
                        {...props}
                        title="Ingrese la cantidad a agregar"
                        initialValues={props.element}
                        onSubmit={(values) => { props.submit(values); }}
                    />
                </ModalBody>
            </Modal>
        </div>
    )
}