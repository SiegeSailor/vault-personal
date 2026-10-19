import { useDispatch, useSelector } from "react-redux";
import type { TypedUseSelectorHook } from "react-redux";

import content from "./content";
import { AnyAction } from "@reduxjs/toolkit";

const _useDispatch = () => {
  const dispatch = useDispatch();
  return (handler: (actions: typeof content.actions) => AnyAction) =>
    dispatch(handler(content.actions));
};
const _useSelector: TypedUseSelectorHook<
  ReturnType<typeof content.store.getState>
> = useSelector;

export default {
  store: content.store,
  useDispatch: _useDispatch,
  useSelector: _useSelector,
};
