import { parseType } from '../src/utils';

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
