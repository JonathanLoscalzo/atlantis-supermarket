import React from 'react';
import { MdSend, MdArrowBack } from 'react-icons/md'

export default ({ submitting, pristine, reset, goBack, ...props }) => (
    <div className="form-row mt-3 d-flex justify-content-center">
        <button
            type="submit"
            className="btn btn-lg btn-primary "
            disabled={submitting}>
            <MdSend /> Enviar
                        </button>
    </div>)