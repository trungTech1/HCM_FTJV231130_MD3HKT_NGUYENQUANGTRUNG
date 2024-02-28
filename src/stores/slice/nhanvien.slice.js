import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
// import { staffApi } from "../../api/staff.api";
import axios from "axios";

const getStaff = createAsyncThunk("staff/getStaff", async () => {
  try {
    const response = await axios.get("http://localhost:3000/staffs");
    return response.data;
  } catch (error) {
    console.log("Failed to fetch staff: ", error);
  }
});
const staffSlice = createSlice({
  name: "staff",
  initialState: {
    staff: [],
  },
  reducers: {
    setStaff: (state, action) => {
      state.staff = action.payload;
    },
    updateStaffStatus: (state, action) => {
      const staff = state.staff.find((item) => item.id === action.payload.id);
      if (staff) {
        staff.status =
          staff.status === "Đang hoạt động"
            ? "Ngừng hoạt động"
            : "Đang hoạt động";
      }
    },
  },
  extraReducers: (builder) => {
    builder.addCase(getStaff.fulfilled, (state, action) => {
      state.staff = action.payload;
    });
  },
});

export const staffActions = { ...staffSlice.actions, getStaff };
export const staffReducer = staffSlice.reducer;
