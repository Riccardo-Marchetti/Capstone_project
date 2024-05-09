import { useState } from "react";
import { Button, Col, Row, Form, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const navigate = useNavigate();

  const fetchRegister = async () => {
    try {
      const response = await fetch("http://localhost:3001/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, name, surname, email, password }),
      });
      if (response.ok) {
        const data = await response.json();
        console.log(data);
        navigate("/");
      } else {
        throw new Error(`${response.status} - Errore nella fetch`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleRegister = (e) => {
    e.preventDefault();
    fetchRegister();
  };

  return (
    <header className="c d-flex align-items-center justify-content-end ">
      <Container className="login-container p-4 m-0 ">
        <Row className="d-flex flex-column ">
          <Col className="text-center">
            <h4 className="mb-3 fw-bold ">Register</h4>
            <p>
              Are you already registered?{" "}
              <Link to="/" className="text-decoration-none ">
                {" "}
                Log in{" "}
              </Link>{" "}
              now!
            </p>
          </Col>

          <Col>
            <Form className="form">
              <Form.Group className="mb-3" controlId="formBasicUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Insert your username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicName">
                <Form.Label>Name</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Insert your name"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicSurname">
                <Form.Label>Surname</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Insert your surname"
                  value={surname}
                  onChange={(e) => setSurname(e.target.value)}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Insert your email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Insert your password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </Form.Group>

              <Button
                variant="primary"
                type="submit"
                className="col-12"
                onClick={handleRegister}
              >
                Register
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </header>
  );
};
export default RegisterPage;
