import { configureStore, createSlice, combineReducers } from "@reduxjs/toolkit";

const theme = createSlice({
  name: "theme",
  initialState: "light" as "light" | "dark",
  reducers: {
    toggle: (state) => {
      switch (state) {
        case "light":
          return "dark";
        case "dark":
          return "light";
      }
    },
  },
});

const isSideBarOpen = createSlice({
  name: "isSideBarOpen",
  initialState: false,
  reducers: {
    toggle: (state) => {
      return !state;
    },
  },
});

const store = configureStore({
  reducer: combineReducers({
    theme: theme.reducer,
    isSideBarOpen: isSideBarOpen.reducer,
  }),
});

export default {
  store,
  actions: {
    theme: theme.actions,
    isSideBarOpen: isSideBarOpen.actions,
  },
};
