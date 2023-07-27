import { useDispatch, useSelector } from "react-redux";
import type { TypedUseSelectorHook } from "react-redux";

import content from "./content";

const _useDispatch: () => typeof content.store.dispatch = useDispatch;
const _useSelector: TypedUseSelectorHook<
  ReturnType<typeof content.store.getState>
> = useSelector;

export default {
  store: content.store,
  useDispatch: _useDispatch,
  useSelector: _useSelector,
};
