import { key, parseType } from '../src/utils';

describe('Utils', () => {
  describe('Functions', () => {
    describe('parseType', () => {
      it('parse non-List type', () => {
        const type = 'Hello';
        const result = parseType(type);
        expect(result).not.toBeUndefined();
        if (result) {
          const { isList, name } = result;
          expect(isList).toBeFalsy;
          expect(name).toBe('Hello');
        }
      });
      it('parse list type', () => {
        const type = 'list<Hello>';
        const result = parseType(type);
        expect(result).not.toBeUndefined();
        if (result) {
          const { isList, name } = result;
          expect(isList).toBeTruthy;
          expect(name).toBe('Hello');
        }
      });
      it('parse falsely used type', () => {
        const type = 'list<Hello';
        const result = parseType(type);
        expect(result).toBeUndefined();
      });
    });
  });
});

describe('key function', () => {
  it('should generate a key for an object with given keys', () => {
    const item = { a: 1, b: 2, c: 3 };
    const keys = ['a', 'b'] as any;
    const result = key(item, keys);
    expect(result).toBe('1_2');
  });

  it('should generate a key for an object with nested properties', () => {
    const item = { a: { b: { c: 1 } } };
    const keys = ['a.b.c'] as any;
    const result = key(item, keys);
    expect(result).toBe('1');
  });

  it('should generate a key for an object with multiple nested properties', () => {
    const item = { a: { b: { c: 1 } }, d: { e: 2 } };
    const keys = ['a.b.c', 'd.e'] as any;
    const result = key(item, keys);
    expect(result).toBe('1_2');
  });

  it('should generate a key for an object with missing properties', () => {
    const item = { a: 1, b: 2 };
    const keys = ['a', 'c'] as any;
    const result = key(item, keys);
    expect(result).toBe('1_');
  });

  it('should generate a key for an object with all missing properties', () => {
    const item = { a: 1, b: 2 };
    const keys = ['c', 'd'] as any;
    const result = key(item, keys);
    expect(result).toBe('_');
  });
});
