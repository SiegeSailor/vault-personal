import { useReducer, createContext, useContext } from "react";
import { useRouter } from "next/router";

import style from "./index.module.scss";

interface IAction {
  type: "UPDATE_SEARCH";
  value: string;
}

interface IState {
  search: string;
}

const initialContext: IState & { dispatch: (action: IAction) => void } = {
  search: "",
  dispatch: () => {},
};

export const Context = createContext(initialContext);

export function useWrappedReducer() {
  return useReducer((state: IState, action: IAction) => {
    switch (action.type) {
      case "UPDATE_SEARCH":
        return { ...state, search: action.value.toLowerCase() };
      default:
        throw new Error();
    }
  }, initialContext);
}

export function useMatch(pathname: string) {
  const router = useRouter();

  return router.asPath === pathname ? style.selected : "";
}

export function useSearch(name: string) {
  const context = useContext(Context);

  return !name.toLowerCase().includes(context.search) ? style.faded : "";
}
