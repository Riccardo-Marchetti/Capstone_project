import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import logo from "../assets/LogoNavbar.png";

const MyNavBar = () => {
  const [user, setUser] = useState([]);
  const getUser = async () => {
    try {
      const response = await fetch("http://localhost:3001/user/me", {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        console.log(data);
        setUser(data);
      } else {
        throw new Error(`${response.status} - Errore nella fetch`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getUser();
  }, []);

  return (
    <>
      <Navbar expand="md" className="bg-body-tertiary">
        <Container fluid>
          <Navbar.Brand href="#">
            <img
              src={logo}
              style={{ width: "55px", height: "55px" }}
              className="rounded-circle object-fit-cover ms-2"
              alt="LOGO"
            />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll">
            <Nav
              className="me-auto my-2 my-md-0"
              style={{ maxHeight: "100px" }}
              navbarScroll
            >
              <Link
                to="/"
                className="text-decoration-none d-flex align-items-center ms-1 ms-md-3 me-4 text-black"
              >
                {" "}
                Home{" "}
              </Link>{" "}
              <Link
                to="/promotions"
                className="text-decoration-none d-flex align-items-center ms-1 me-4 text-black"
              >
                {" "}
                Promotions{" "}
              </Link>{" "}
            </Nav>
            <Form className="d-flex search-film ">
              <Form.Control
                type="search"
                placeholder="Search movies..."
                className="me-2"
                aria-label="Search"
              />
              <Button variant="outline-success">Search</Button>
            </Form>
            <Link to={"/profile"} className="text-decoration-none d-flex   ">
              <div className="d-flex align-items-center justify-content-start mt-2 mt-md-0 ">
                <Button className="rounded-circle ms-md-3 mt-1 mb-1 mb-md-0  mt-md-0 p-0  button-login d-flex align-items-center bg-transparent border-0">
                  <img
                    className=" rounded-circle object-fit-cover"
                    src={user.avatar}
                    style={{ width: "45px", height: "45px" }}
                    alt="profile-image"
                  />
                </Button>
              </div>
            </Link>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
};
export default MyNavBar;
