import { Injection, FlatSchema } from './types';
import { inject, makeGroups } from './utils';

export const load = (schemas: FlatSchema[], data: Record<string, object[]>) => {
  const indexes: Record<string, Record<string, object[]>> = {};
  schemas.forEach((schema) => {
    const thisData = data[schema.name];
    if (!thisData) {
      return;
    }

    schema.indexes?.forEach((index) => {
      const { name, keys } = index;
      indexes[name] = makeGroups<any>(thisData, keys);
    });
  });

  schemas.forEach((schema) => {
    const thisData = data[schema.name];
    const thisInjections = schema.injections;
    if (thisData && thisInjections) {
      thisInjections.forEach((injection: Injection) => {
        inject(thisData, injection, indexes);
      });
    }
  });

  const entry = schemas?.length > 0 ? schemas[0].name : undefined;
  return entry ? data[entry] : undefined;
};
