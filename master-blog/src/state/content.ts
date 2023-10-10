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

const sectionsInitialState = Object.keys(config.constants.SECTION_NAMES).reduce<
  Record<string, { mount: boolean; isVisible: boolean }>
>((result, key) => {
  result[key.toLowerCase()] = { mount: false, isVisible: false };
  return result;
}, {});
const sections = createSlice({
  name: "sections",
  initialState: sectionsInitialState,
  reducers: {
    mount: (state, action: PayloadAction<string>) => {
      return {
        ...state,
        [action.payload]: { ...state[action.payload], mount: true },
      };
    },
    view: (state, action: PayloadAction<string>) => {
      return {
        ...state,
        ...Object.keys(state).reduce<typeof sectionsInitialState>(
          (result, key) => {
            result[key] = { mount: state[key].mount, isVisible: false };
            return result;
          },
          {}
        ),
        [action.payload]: { ...state[action.payload], isVisible: true },
      };
    },
  },
});

const store = configureStore({
  reducer: combineReducers({
    theme: theme.reducer,
    isSideBarOpen: isSideBarOpen.reducer,
    sections: sections.reducer,
  }),
});

export default {
  store,
  actions: {
    theme: theme.actions,
    isSideBarOpen: isSideBarOpen.actions,
    sections: sections.actions,
  },
};
