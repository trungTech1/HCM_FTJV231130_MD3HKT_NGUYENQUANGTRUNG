import { configureStore } from "@reduxjs/toolkit";
import { staffReducer } from "./slice/nhanvien.slice";

const store = configureStore({
  reducer: {
    staff: staffReducer,
  },
});

export default store;
