import { useRef } from "react";
import { Button } from "react-bootstrap";

const UploadProfileImage = () => {
  const inputRef = useRef();

  const handleClick = () => {
    inputRef.current.click();
  };

  const changeProfileImage = async () => {
    let file = inputRef.current.files[0];
    if (file) {
      let formData = new FormData();
      formData.append("avatar", file);

      try {
        const response = await fetch("http://localhost:3001/user/me/upload", {
          method: "POST",
          body: formData,
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        if (response.ok) {
          const data = await response.json;
          console.log(data);
          window.location.reload();
        }
      } catch (error) {
        console.log(error);
      }
    }
  };
  return (
    <>
      <input
        type="file"
        ref={inputRef}
        style={{ display: "none" }}
        onChange={changeProfileImage}
      />
      <Button
        className="profile-but-img rounded-circle border-black  bg-transparent mt-3 me-1"
        style={{ width: "45px", height: "45px" }}
        onClick={handleClick}
      >
        <i className="fas fa-camera"></i>
      </Button>
    </>
  );
};
export default UploadProfileImage;
