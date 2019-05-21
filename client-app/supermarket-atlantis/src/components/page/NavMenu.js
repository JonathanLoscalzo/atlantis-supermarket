import React from 'react'
import { NavLink } from 'react-router-dom'
import { UncontrolledDropdown, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

export default class NavMenu extends React.Component {
    state = {
        dropdownOpen: false
    };

    toggle = () => {
        this.setState(prevState => ({
            dropdownOpen: !prevState.dropdownOpen
        }));
    }

    render() {

        return (
            <nav class="navbar navbar-expand-lg navbar-transparent  bg-primary  navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-translate">
                        <a class="navbar-brand" href="/presentation.html">Atlantis</a>
                    </div>

                    <div class="collapse navbar-collapse justify-content-end">
                        <ul class="navbar-nav">


                            <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle} nav inNavbar>
                                <DropdownToggle caret>
                                    <i class="now-ui-icons users_single-02"></i>
                                </DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem header>Opciones</DropdownItem>
                                    <NavLink className="dropdown-item" to="/product"> Editar </NavLink>
                                    <DropdownItem divider />
                                    <NavLink className="dropdown-item" to="/logout"> Salir </NavLink>
                                </DropdownMenu>
                            </Dropdown>
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}