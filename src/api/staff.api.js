import axios from "axios";

export const staffApi = {
  getStaff: async () => {
    return await axios.get("http://localhost:3000/staffs");
  },
  addStaff: async (staff) => {
    return await axios.post("http://localhost:3000/staffs", staff);
  },
  updateStaff: async (staff) => {
    return await axios.put(`http://localhost:3000/staffs/${staff.id}`, staff);
  },
  deleteStaff: async (id) => {
    return await axios.delete(`http://localhost:3000/staffs/${id}`);
  },
};
