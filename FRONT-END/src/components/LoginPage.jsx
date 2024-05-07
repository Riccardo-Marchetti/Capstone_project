import { useState } from "react";
import { Button, Col, Row, Form, Container } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const fetchLogin = async () => {
    try {
      const response = await fetch("http://localhost:3001/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });
      if (response.ok) {
        const data = await response.json();
        console.log(data);
        localStorage.setItem("token", data.accessToken);
        navigate("/register");
        window.location.reload();
      } else {
        throw new Error(`${response.status} - Errore nella fetch`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleLogin = (e) => {
    e.preventDefault();
    fetchLogin();
  };

  return (
    <header className="c d-flex align-items-center justify-content-end ">
      <Container className="login-container p-4 m-0 ">
        <Row className="d-flex flex-column ">
          <Col className="text-center">
            <h4 className="mb-3 fw-bold ">Login</h4>
            <p>
              Not registered yet?{" "}
              <Link to="/register" className="text-decoration-none ">
                {" "}
                Sign up{" "}
              </Link>{" "}
              now!
            </p>
          </Col>
          <Col>
            <Form>
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
                className=" col-12"
                onClick={handleLogin}
              >
                LOGIN
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </header>
  );
};
export default LoginPage;
