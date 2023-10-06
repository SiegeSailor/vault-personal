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

const isSideMenuOpen = createSlice({
  name: "isSideMenuOpen",
  initialState: true,
  reducers: {
    toggle: (state) => {
      return !state;
    },
  },
});

const store = configureStore({
  reducer: combineReducers({
    theme: theme.reducer,
    isSideMenuOpen: isSideMenuOpen.reducer,
  }),
});

export default {
  store,
  actions: {
    theme: theme.actions,
    isSideMenuOpen: isSideMenuOpen.actions,
  },
};
