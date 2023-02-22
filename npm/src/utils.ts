import _ from 'lodash';

import { Injection } from './types';

const KEY_SEP = '_';

export const inject = <P extends object, C>(
  items: P[],
  injection: Injection,
  indexes: Record<string, Record<string, C[]>>,
) => {
  const { name, type, index, fkeys } = injection;
  items.forEach((item) => {
    const itemKey = key(item, fkeys as any);
    const values = _.get(indexes[index], itemKey);

    const isList = type ? parseType(type)?.isList : true;
    if (isList) {
      _.set(item, name, values);
    } else {
      _.set(item, name, _.first(values));
    }
  });
};

export const key = <T>(item: T, keys: (keyof T)[]) => {
  const value = keys.map((k) => _.get(item, k)).join(KEY_SEP);
  return value;
};

export const makeIndex = <T>(items: T[] | undefined, keys: (keyof T)[]) => {
  return _.mapKeys(items, (item: T) => key(item, keys));
};

export const makeGroups = <T>(items: T[] | undefined, keys: (keyof T)[]) => {
  return _.groupBy(items, (item: T) => key(item, keys));
};

const patternList = /^list<([a-zA-Z]\w*)>$/;
const patternOne = /^(\w+)$/;
export const parseType = (type: string) => {
  const matchList = type.match(patternList);
  const matchOne = type.match(patternOne);
  if (matchList) {
    const [, name] = matchList;
    return { isList: true, name };
  } else if (matchOne) {
    const [, name] = matchOne;
    return { isList: false, name };
  }

  return undefined;
};
