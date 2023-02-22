export declare type FlatSchema = {
  name: string;
  fields: Field[];
  comment?: string;
  injections?: Injection[];
  indexes?: Index[];
};

export declare type Injection = Field & {
  index: string;
  fkeys: string[];
};

declare type Index = {
  name: string;
  keys: string[];
};

declare type Field = {
  name: string;
  type?: string;
  comment?: string;
};
