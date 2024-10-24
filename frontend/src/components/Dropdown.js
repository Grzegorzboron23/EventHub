import React from 'react';
import '../styles/dropdown.css';

const Dropdown = ({ buttonLabel, items,className, onItemClick  }) => {
  return (
    <div className={`dropdown customDropdown ${className ? className : ''}`}> 
      <button className="dropdown-toggle transform-12" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        {buttonLabel}
      </button>
      <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
        {items.map((item, index) => (
          <a key={index} className="dropdown-item"  onClick={() => onItemClick(item)}>
            {item}
          </a>
        ))}
      </div>
    </div>
  );
};



export default Dropdown;