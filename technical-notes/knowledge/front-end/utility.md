# Utility

## Overall

The place where I store useful functions.

## Parse

Functions used to decode or parse values.

### Hex 6 to Hexadecimal

```typescript
export const parseHex = (code: string): number => { 
    return parseInt(replace(code, '#', ''), 16); 
};
```

### RGBA to Hex

```typescript
export const parseRgba = (code: string): string => { 
    const codeFiltered: string[] = code.match( /^rgba?[\s+]?([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i ); 
    return codeFiltered && 
    codeFiltered.length === 4 ? '#' + ('0' + parseInt(codeFiltered[1], 10).toString(16)).slice(-2)
        + ('0' + parseInt(codeFiltered[2], 10).toString(16)).slice(-2)
        + ('0' + parseInt(codeFiltered[3], 10).toString(16)).slice(-2) : '';
};
```

