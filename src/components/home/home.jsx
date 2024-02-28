import React, { useEffect, useState } from "react";
import "./home.scss";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import { staffActions } from "../../stores/slice/nhanvien.slice";
import { useForm } from "react-hook-form";

const Home = () => {
  const dispatch = useDispatch();
  const staff = useSelector((store) => store.staff);
  const staffs = staff.staff;
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [StaffToDelete, setStaffToDelete] = useState({});
  const [formMode, setFormMode] = useState("new");
  const [editingStaff, setEditingStaff] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [showConfirmModal, setShowConfirmModal] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    dispatch(staffActions.getStaff());
  }, [dispatch]);
  const deleteStaff = (id) => {
    setShowDeleteModal(true);
    setStaffToDelete(id);
  };
  const deleteStaffConfirm = (id) => {
    axios
      .delete(`http://localhost:3000/staffs/${id}`)
      .then(() => {
        dispatch(staffActions.getStaff());
        setShowDeleteModal(false);
      })
      .catch((error) => {
        console.log("Failed to delete staff: ", error);
      });
  };
  const editStaff = async (staff) => {
    const name = document.getElementById("userName").value;
    const dateOfBirth = document.getElementById("dateOfBirth").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const editedStaff = {
      id: staff.id,
      name: name,
      dateOfBirth: dateOfBirth,
      email: email,
      address: address,
      status: staff.status,
    };
    await axios
      .put(`http://localhost:3000/staffs/${staff.id}`, editedStaff)
      .then(() => {
        dispatch(staffActions.getStaff());
        document.getElementById("userName").value = "";
        document.getElementById("dateOfBirth").value = "";
        document.getElementById("email").value = "";
        document.getElementById("address").value = "";
        setShowForm(false);
      })
      .catch((error) => {
        console.log("Failed to edit staff: ", error);
      });
  };
  const addNewStaff = async () => {
    setFormMode("new");
    const name = document.getElementById("userName").value;
    const dateOfBirth = document.getElementById("dateOfBirth").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const newStaff = {
      name: name,
      dateOfBirth: dateOfBirth,
      email: email,
      address: address,
      status: "Đang hoạt động",
    };
    await axios
      .post(`http://localhost:3000/staffs`, newStaff)
      .then(() => {
        dispatch(staffActions.getStaff());
        document.getElementById("userName").value = "";
        document.getElementById("dateOfBirth").value = "";
        document.getElementById("email").value = "";
        document.getElementById("address").value = "";
        setShowForm(false);
      })
      .catch((error) => {
        console.log("Failed to add new staff: ", error);
      });
  };
  const onSubmit = () => {
    if (formMode === "new") {
      addNewStaff();
    } else {
      editStaff(editingStaff);
    }
  };

  return (
    <>
      <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossOrigin="anonymous"
      />
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossOrigin="anonymous"
        referrerPolicy="no-referrer"
      />

      <div className="w-[80%] m-auto mt-4 h-[100vh]">
        <main className="main">
          <header className="d-flex justify-content-between mb-3">
            <h3>Nhân viên</h3>
            <button
              className="btn btn-primary"
              onClick={() => {
                setFormMode("new");
                setShowForm(true);
              }}
            >
              Thêm mới nhân viên
            </button>
          </header>
          <div className="d-flex align-items-center justify-content-end gap-2 mb-3">
            <input
              style={{ width: "350px" }}
              type="text"
              className="form-control"
              placeholder="Tìm kiếm theo email"
            />
            <i className="fa-solid fa-arrows-rotate" title="Refresh"></i>
          </div>

          <table className="table table-bordered table-hover table-striped">
            <thead>
              <tr>
                <th>STT</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Email</th>
                <th>Địa chỉ</th>
                <th>Trạng thái</th>
                <th colSpan="2">Chức năng</th>
              </tr>
            </thead>
            <tbody>
              {staffs.map((item, index) => {
                return (
                  <tr key={index}>
                    <td>{index + 1}</td>
                    <td>{item.name}</td>
                    <td>{item.dateOfBirth}</td>
                    <td>{item.email}</td>
                    <td>{item.address}</td>
                    <td>
                      <div
                        style={{
                          display: "flex",
                          alignItems: "center",
                          gap: "8px",
                        }}
                      >
                        <div
                          className={`status ${
                            item.status === "Đang hoạt động"
                              ? "status-active"
                              : "status-inactive"
                          }`}
                        ></div>
                        <span> {item.status}</span>
                      </div>
                    </td>
                    <td>
                      <span
                        className="button button-block"
                        onClick={() => {
                          dispatch(
                            staffActions.updateStaffStatus({ id: item.id })
                          );
                        }}
                      >
                        Chặn
                      </span>
                    </td>
                    <td>
                      <span
                        className="button button-edit"
                        onClick={() => {
                          setFormMode("edit");
                          setEditingStaff(item);
                          setShowForm(true);
                        }}
                      >
                        Sửa
                      </span>
                    </td>
                    <td>
                      <span
                        className="button button-delete"
                        onClick={() => deleteStaff(item.id)}
                      >
                        Xóa
                      </span>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
          <footer className="d-flex justify-content-end align-items-center gap-3">
            <select className="form-select" value="10">
              <option value="10">Hiển thị 10 bản ghi trên trang</option>
              <option value="20">Hiển thị 20 bản ghi trên trang</option>
              <option value="50">Hiển thị 50 bản ghi trên trang</option>
              <option value="100">Hiển thị 100 bản ghi trên trang</option>
            </select>
            <ul className="pagination">
              <li className="page-item">
                <a className="page-link" href="#">
                  Previous
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  1
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  2
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  3
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  Next
                </a>
              </li>
            </ul>
          </footer>
        </main>
      </div>

      {/* <!-- Form thêm mới nhân viên --> */}
      <div className="overlay" hidden={!showForm}>
        <form className="form" onSubmit={handleSubmit(onSubmit)}>
          <div className="d-flex justify-content-between align-items-center">
            <h4>
              {formMode === "new"
                ? "Thêm mới nhân viên"
                : "Chỉnh sửa nhân viên"}
            </h4>
            <i
              className="fa-solid fa-xmark"
              onClick={() => setShowForm(false)}
            ></i>
          </div>
          <div>
            <label className="form-label" htmlFor="userName">
              Họ và tên
            </label>
            <input
              id="userName"
              type="text"
              className="form-control"
              {...register("name", { required: true })}
            />
            {errors.name && (
              <div className="form-text error">Vui lòng nhập họ và tên</div>
            )}
          </div>
          <div>
            <label className="form-label" htmlFor="dateOfBirth">
              Ngày sinh
            </label>
            <input
              id="dateOfBirth"
              type="date"
              className="form-control"
              {...register("dateOfBirth", { required: true })}
            />
            {errors.dateOfBirth && (
              <div className="form-text error">Vui lòng chọn ngày sinh</div>
            )}
          </div>
          <div>
            <label className="form-label" htmlFor="email">
              Email
            </label>
            <input
              id="email"
              type="text"
              className="form-control"
              {...register("email", {
                required: true,
                pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
              })}
            />
            {errors.email && errors.email.type === "required" && (
              <div className="form-text error">Vui lòng nhập email</div>
            )}
            {errors.email && errors.email.type === "pattern" && (
              <div className="form-text error">Email không hợp lệ</div>
            )}
          </div>
          <div>
            <label className="form-label" htmlFor="address">
              Địa chỉ
            </label>
            <textarea
              className="form-control"
              id="address"
              rows="3"
              {...register("address", { required: true })}
            />
            {errors.address && (
              <div className="form-text error">Vui lòng nhập địa chỉ</div>
            )}
          </div>
          <div>
            <button type="submit">Submit</button>
          </div>
        </form>
      </div>
      {/* <!-- Modal xác nhận chặn tài khoản --> */}
      <div className="overlay" hidden>
        <div className="modal-custom">
          <div className="modal-title">
            <h4>Cảnh báo</h4>
            <i className="fa-solid fa-xmark"></i>
          </div>
          <div className="modal-body-custom">
            <span>Bạn có chắc chắn muốn chặn tài khoản này?</span>
          </div>
          <div className="modal-footer-custom">
            <button className="btn btn-light">Hủy</button>
            <button className="btn btn-danger">Xác nhận</button>
          </div>
        </div>
      </div>

      {/* <!-- Modal xác nhận xóa tài khoản --> */}
      <div className="overlay" hidden={!showDeleteModal}>
        <div className="modal-custom">
          <div className="modal-title">
            <h4>Cảnh báo</h4>
            <i
              className="fa-solid fa-xmark"
              onClick={() => setShowDeleteModal(false)}
            ></i>
          </div>
          <div className="modal-body-custom">
            <span>Bạn có chắc chắn muốn xóa tài khoản này?</span>
          </div>
          <div className="modal-footer-custom">
            <button
              className="btn btn-light"
              onClick={() => setShowDeleteModal(false)}
            >
              Hủy
            </button>
            <button
              className="btn btn-danger"
              onClick={() => deleteStaffConfirm(StaffToDelete)}
            >
              Xác nhận
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
