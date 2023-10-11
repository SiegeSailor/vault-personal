import {
  configureStore,
  createSlice,
  combineReducers,
  PayloadAction,
} from "@reduxjs/toolkit";

import config from "@/config";

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

const section = createSlice({
  name: "section",
  initialState: config.constants.SECTION_NAMES.ABOUT,
  reducers: {
    set: (_, action: PayloadAction<string>) => {
      return action.payload;
    },
  },
});

const store = configureStore({
  reducer: combineReducers({
    theme: theme.reducer,
    isSideBarOpen: isSideBarOpen.reducer,
    section: section.reducer,
  }),
});

export default {
  store,
  actions: {
    theme: theme.actions,
    isSideBarOpen: isSideBarOpen.actions,
    section: section.actions,
  },
};
