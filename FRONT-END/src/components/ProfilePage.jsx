import { Col, Row } from "react-bootstrap";
import MyNavBar from "./MyNavBar";
import { useEffect, useState } from "react";
import UploadProfileImage from "./UploadProfileImage";

const ProfilePage = () => {
  const [user, setUser] = useState([]);
  const [ticket, setTicket] = useState([]);

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

  const getTicket = async () => {
    try {
      const response = await fetch("http://localhost:3001/user/me/tickets", {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        console.log(data);
        setTicket(data);
      } else {
        throw new Error(`${response.status} - Errore nella fetch`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getUser();
    getTicket();
  }, []);
  return (
    <>
      <MyNavBar />
      <Row className="mt-4 d-flex justify-content-center mx-3 ">
        <Col className="col-12 col-sm-10 col-md-5 col-xxl-3 profile-col me-md-4">
          <Row>
            <Col className="col-6 d-flex justify-content-start ps-4">
              <img
                src={user.avatar}
                alt="profile-img"
                className="rounded-circle object-fit-cover mt-3 mb-3 profile-img"
              />
            </Col>
            <Col className="col-6 d-flex justify-content-end align-items-start">
              <UploadProfileImage />
            </Col>
          </Row>
          <Col className="mb-4 ">
            <h6>
              Full name:{" "}
              <span>
                {user.name} {user.surname}
              </span>
            </h6>
            <h6>
              Username: <span>{user.username}</span>
            </h6>
            <h6>
              Email: <span>{user.email}</span>
            </h6>
          </Col>
        </Col>
        <Col className="col-12 col-sm-10 col-md-6 col-xl-5 col-xxl-6 mt-3 mt-md-0 tickets-col ms-md-1 ">
          <div className="d-flex justify-content-center mt-3 ">
            <h5>My tickets :</h5>
          </div>
          {ticket && ticket.length > 0 ? (
            ticket.map((tick, i) => {
              return (
                <>
                  <div key={i}>
                    <h6>ciao</h6>
                  </div>
                </>
              );
            })
          ) : (
            <div className="d-flex justify-content-center align-items-center ">
              <p>You haven&apos;t booked any tickets</p>
            </div>
          )}
        </Col>
      </Row>
    </>
  );
};
export default ProfilePage;
