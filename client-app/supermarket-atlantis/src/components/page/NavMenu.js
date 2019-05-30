import React from 'react'
import { NavLink } from 'react-router-dom'

import { FaUserCircle } from "react-icons/fa";

import {
  UncontrolledDropdown, Dropdown, DropdownToggle, DropdownMenu, DropdownItem,
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
} from 'reactstrap';

export default class NavMenu extends React.Component {
  state = {
    dropdownOpen: false,
    isOpen: false
  };

  toggle = () => {
    this.setState(prevState => ({
      dropdownOpen: !prevState.dropdownOpen

    }));
  }

  toggleOpen = () => {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  render() {

    return (
      <header>
        <Navbar className="navbar-dark bg-dark" light expand="md">
          <NavbarBrand href="/">reactstrap</NavbarBrand>
          <NavbarToggler onClick={this.toggleOpen} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle} nav inNavbar>
                <DropdownToggle caret tag="a">
                  <FaUserCircle style={{color:"white"}}/>
              </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem header>Opciones</DropdownItem>
                  <NavLink className="dropdown-item" to="/product"> Editar </NavLink>
                  <DropdownItem divider />
                  <NavLink className="dropdown-item" to="/logout"> Salir </NavLink>
                </DropdownMenu>
              </Dropdown>
            </Nav>
          </Collapse>
        </Navbar>
      </header>
    )
  }
}