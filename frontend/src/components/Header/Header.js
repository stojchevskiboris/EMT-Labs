import Container from 'react-bootstrap/Container';
import { Link } from 'react-router-dom';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

function Header() {
    return (
        <Navbar bg="info" expand="md">
            <Container>
                <Navbar.Brand className="" href="/">Book Store</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link className="nav-link fw-bolder" to={"/books"}>Books</Link>
                        <Link className="nav-link fw-bolder" to={"/categories"}>Categories</Link>
                        {/*<Link className="nav-link" href="/categories">Link</Link>*/}
                        <NavDropdown title="More" id="basic-nav-dropdown" className=" fw-bolder">
                            <NavDropdown.Item href="https://github.com/stojchevskiboris/EMT-Labs">Github Repo</NavDropdown.Item>
                            {/*<NavDropdown.Item href="#action/3.2">*/}
                            {/*    Another action*/}
                            {/*</NavDropdown.Item>*/}
                            {/*<NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>*/}
                            {/*<NavDropdown.Divider />*/}
                            {/*<NavDropdown.Item href="#action/3.4">*/}
                            {/*    Separated link*/}
                            {/*</NavDropdown.Item>*/}
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;